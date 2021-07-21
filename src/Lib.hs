module Lib
  ( someFunc
  ) where

import           ClassyPrelude          (Char, Double, IO, Integer, Show, Text,
                                         fromIntegral, length, map, putStrLn,
                                         show, sum, toInteger, unzip4, (*), (+),
                                         (-), (.), (/), (<), (<=), (==), (>),
                                         (^))
import           Data.List              (nub)
import qualified Data.Text              as DT (head, singleton, snoc, tail,
                                               take, unpack)
import           Markov.Count           (count)
import qualified Text.PrettyPrint.Boxes as B (Box, left, printBox, text, vcat,
                                              (<+>))

k :: Integer
k = 0

text :: Text
text =
  "##liet do sta rasti nam biez starosti hod ot hoda rasti našiej bodrosti slavtie molot i stich ziemliu molodosti#"

lengthI :: Text -> Integer
lengthI = toInteger . length

countText :: Text -> Integer
countText = count text

count' :: Text -> Double
count' x =
  fromIntegral (countText x + 1) * snC (lengthI x) (countText x + 1) /
  snC (lengthI x) (countText x)

snC :: Integer -> Integer -> Double
snC len r =
  let nc = nC len r
   in if nc > 0
        then fromIntegral nc
        else 0.0

nC :: Integer -> Integer -> Integer
nC 0 _ = 0
nC len 0 =
  (toInteger . length . nub) (DT.unpack text) ^ len -
  sum (map (nC len) [1 .. (toInteger . length . nub) (subLists len text)])
nC len r =
  let sub = nub (subLists len text)
   in (toInteger . length)
        [y | y <- sub, (toInteger . length) y == len, countText y == r]

subLists :: Integer -> Text -> [Text]
subLists len t =
  if lengthI t < len
    then []
    else DT.take (fromIntegral len) t : subLists len (DT.tail t)

alpha :: Char -> Text -> Double
alpha s wrd =
  beta s wrd /
  (sum . map (\x -> pBo x (DT.tail wrd)))
    [y | y <- nub (DT.unpack text), countText (DT.snoc wrd y) <= k]

beta :: Char -> Text -> Double
beta _ wrd = 1.0 - (sum . map (`pBo` wrd))
    [y | y <- nub (DT.unpack text), countText (DT.snoc wrd y) > k]

pBo :: Char -> Text -> Double
pBo s "" = count' (DT.singleton s)
pBo s wrd =
  let wholeWrd = DT.snoc wrd s
   in if countText wholeWrd > k
        then count' wholeWrd / fromIntegral (countText wrd)
        else alpha s wrd * pBo s (DT.tail wrd)

someFunc :: IO ()
someFunc = B.printBox (makeTable (makePrList 2))

makePrList :: Integer -> [(Text, Double, Double, Double)]
makePrList len = map makePr (subLists len text)

makePr :: Text -> (Text, Double, Double, Double)
makePr "" = ("", 0, 0, 0)
makePr t =
  ( t
  , alpha (DT.head t) (DT.tail t)
  , beta (DT.head t) (DT.tail t)
  , pBo (DT.head t) (DT.tail t))

makeTable :: (Show a, Show b, Show c, Show d) => [(a, b, c, d)] -> B.Box
makeTable list = makeRows (unzip4 list)
  where
    makeRows (a, b, c, d) =
      makeColumn a B.<+> makeColumn b B.<+> makeColumn c B.<+> makeColumn d
    makeColumn l = B.vcat B.left (map (B.text . show) l)

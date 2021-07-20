module Lib
  ( someFunc
  ) where

import           ClassyPrelude (Char, Double, IO, Integer, Text, fromIntegral,
                                length, map, putStrLn, sum, toInteger, (*), (+),
                                (-), (.), (/), (<), (<=), (==), (>), (^))
import           Data.List     (nub)
import qualified Data.Text     as DT (snoc, tail, take, unpack)
import           Markov.Count  (count)

k :: Integer
k = 0

text :: Text
text =
  "##liet do sta rasti nam biez starosti hod ot hoda rasti našiej bodrosti slavtie molot i stich ziemliu molodosti#"

countText :: Text -> Integer
countText = count text

count' :: Text -> Double
count' x =
  fromIntegral
    ((countText x + 1) * snC ((toInteger . length) x) (countText x + 1)) /
  fromIntegral (snC ((toInteger . length) x) (countText x))

snC :: Integer -> Integer -> Integer
snC = nC

nC :: Integer -> Integer -> Integer
nC 0 _ = 0
nC len 0 =
  ((toInteger . length . nub) (DT.unpack text) ^ len) -
  sum (map (nC len) [1 .. (toInteger . length . nub) (subLists len text)])
nC len r =
  let sub = nub (subLists len text)
   in (toInteger . length)
        [y | y <- sub, (toInteger . length) y == len, countText y == r]

subLists :: Integer -> Text -> [Text]
subLists len t =
  if (toInteger . length) t < len
    then []
    else DT.take (fromIntegral len) t : subLists len (DT.tail t)

alpha :: Char -> Text -> Double
alpha s wrd =
  beta s wrd /
  (sum . map (\x -> pBo x (DT.tail wrd)))
    [y | y <- nub (DT.unpack text), countText (DT.snoc wrd y) <= k]

beta :: Char -> Text -> Double
beta _ _ = 1.0

pBo :: Char -> Text -> Double
pBo s wrd =
  let wholeWrd = DT.snoc wrd s
   in if countText wholeWrd > k
        then count' wholeWrd / fromIntegral (countText wrd)
        else 0

someFunc :: IO ()
someFunc = putStrLn "someFunc"

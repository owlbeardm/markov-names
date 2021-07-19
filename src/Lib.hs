module Lib
  ( someFunc
  ) where

import           ClassyPrelude (Double, IO, Int, IsSequence (subsequences),
                                Text, fromIntegral, length, putStrLn, take,
                                toInteger, when, (*), (+), (.), (/), (<), (==),
                                (>), tail)
import           Data.Text     (append)
import           Markov.Count  (count)

k :: Int
k = 0

text :: Text
text = "axaxaxa"

countText :: Text -> Int
countText = count text

count' :: Text -> Double
count' x =
  fromIntegral
    ((countText x + 1) * nC (length x) (countText x + 1)) /
  fromIntegral (nC (length x) (countText x))

nC :: Int -> Int -> Int
nC len r =
  let sub = sub' text
   in length
        [y | y <- sub, length y == len, countText y == r]
  where
    sub' t =
      if length t < len
        then []
        else (take len t):(sub' (tail t))

pBo :: Text -> Text -> Double
pBo s wrd =
  let wholeWrd = append wrd s
   in if countText wholeWrd > k
        then count' wholeWrd / fromIntegral (countText wrd)
        else 0

someFunc :: IO ()
someFunc = putStrLn "someFunc"

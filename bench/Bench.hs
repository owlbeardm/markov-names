import           ClassyPrelude
import           Criterion.Main (bench, defaultMain, whnf)
import           Markov.Count   (count)

isPrime :: Integer -> Bool
isPrime n = all notDividedBy [2 .. n - 1]
  where
    notDividedBy m = n `mod` m /= 0

primeNumber :: Integer
primeNumber = 16183

main :: IO ()
main = defaultMain [
  bench "isPrime" $ whnf isPrime primeNumber,
  bench "count" $ whnf (count "axaxaxaxaxa") "ax",
  bench "count" $ whnf (count "axaxaxaxaxa") "a"
  ]

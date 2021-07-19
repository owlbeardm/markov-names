module Lib
  ( someFunc
  ) where

import           ClassyPrelude (IO, putStrLn)

someFunc :: IO ()
someFunc = putStrLn "someFunc"

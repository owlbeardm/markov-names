module Markov.Count
  ( count
  ) where

import           ClassyPrelude (Integer, Text, sum, isPrefixOf)
import           Data.Text (tails)

count :: Text -> Text -> Integer
count "" _ = 0
count _ "" = 0
count text sub = sum [ 1 | r <- tails text, sub `isPrefixOf` r ]


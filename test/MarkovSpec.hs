module MarkovSpec where

import           ClassyPrelude
import           Markov.Count
import           Test.Hspec

markovSpecs :: Spec
markovSpecs = describe "count" $ do
  it "count in \"ax\" \"axaxaxa\"" $
    count "axaxaxa" "ax" == 3
  it "count in \"a\" \"axaxaxa\"" $
    count "axaxaxa" "a" == 4

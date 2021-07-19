import           ClassyPrelude
import           MarkovSpec       (markovSpecs)
import           Test.Hspec       ()
import           Test.Tasty       (defaultMain, testGroup)
import           Test.Tasty.Hspec (testSpecs)

main :: IO ()
main = do
  specs <- concat <$> mapM testSpecs [markovSpecs]
--   goldens <- goldenTests
  defaultMain
    (testGroup
       " All Tests"
       [ testGroup "Specs" specs
                -- , testGroup "Properties" props
                -- , testGroup "Golden Tests" goldens
       ])

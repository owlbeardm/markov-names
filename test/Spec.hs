import           ClassyPrelude
import           Test.Hspec
import           Test.Tasty
import           Test.Tasty.Hspec

main :: IO ()
main = do
  specs <- concat <$> mapM testSpecs
             [ 
             ]
--   goldens <- goldenTests
  defaultMain (testGroup " All Tests" [
                  testGroup "Specs" specs
                -- , testGroup "Properties" props
                -- , testGroup "Golden Tests" goldens
                ])

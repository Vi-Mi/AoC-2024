import Data.List (sort)
import Data.ByteString (count)
import Control.Monad.RWS.Class (MonadState(put))

distance :: Int -> Int -> Int
distance x y = if x > y then (x-y)
               else (y-x)

splitX :: String -> [String]
splitX x = words x

sumOfDistances :: [Int] -> [Int] -> Int
sumOfDistances [] [] = 0
sumOfDistances (x:xs) (y:ys) = (distance x y) + sumOfDistances xs ys
                             

divideToTwo :: [[String]] -> ([Int],[Int])
divideToTwo [] = ([],[])
divideToTwo ((x:(y:ys)):xs) = ((read x) : ax, (read y) : bx)
                         where (ax, bx) = divideToTwo xs
out :: [String] -> Int
out input = sumOfDistances (sort xs) (sort ys)
             where (xs, ys) = divideToTwo (map splitX input)

countX :: Int -> [Int] -> Int
countX x ys = snd $ foldl (\(x, num) y -> if x==y then (x, (num+1))
                                 else (x, num)) (x, 0) ys

countAll :: [Int] -> [Int] -> Int
countAll [] _ = 0
countAll (x:xs) ys = (x * (countX x ys)) + (countAll xs ys)

out2 :: [String] -> Int
out2 input = countAll xs ys
             where (xs, ys) = divideToTwo (map splitX input)


main :: IO ()
main = do
   s <- readFile "input1task.txt"
   let input = (lines s)
   print (input)
   --1
   --putStrLn (show (out input))
   --2
   putStrLn (show (out2 input))


   --print (incr $ join3 input (tail input) (drop 2 input))
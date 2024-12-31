package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

func checkIfItIsGood(list []int) int {
	fmt.Println(list)
	increasing := true
	if list[0] > list[1] {
		increasing = false
	} else if list[0] == list[1] {
		fmt.Print("rovna sa")
		return 0
	}
	for i := 0; i < len(list)-1; i++ {
		one := list[i]
		two := list[i+1]
		if increasing {
			difference := two - one
			if difference < 1 || difference > 3 {
				return 0
			}
		} else {
			difference := one - two
			if difference < 1 || difference > 3 {
				return 0
			}
		}
	}
	return 1
}

func checkSum(list [][]int) int {
	out := 0
	for _, line := range list {
		out += checkIfItIsGood(line)
		fmt.Println(out)
	}
	return out
}

func remove(list []int, index int) []int {
	return append(list[:index], list[index+1:]...)
}

func doCycle(list []int) int {
	fmt.Println(list)
	//var list2 []int
	for i := 0; i < len(list); i++ {
		list2 := make([]int, len(list))
		copy(list2, list)
		list3 := remove(list2, i)
		//list2 := append(list[:i], list[i+1:]...)
		fmt.Println(list)
		fmt.Println(list3)
		if checkIfItIsGood(list3) == 1 {
			fmt.Println(1)
			return 1
		}
	}
	fmt.Println(0)
	return 0
}

func checkSum2(list [][]int) int {
	out := 0
	for _, line := range list {
		out += doCycle(line)
		//fmt.Println(out)
	}
	return out
}

func main() {
	f, err := os.Open("input.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer f.Close()

	var list []string

	scanner := bufio.NewScanner(f)

	for scanner.Scan() {
		list = append(list, scanner.Text())
	}
	var list2 [][]int
	for _, l := range list {
		words := strings.Fields(l)
		var words2 []int
		for _, w := range words {
			num, err := strconv.Atoi(w)
			if err != nil {
				log.Fatal(err)
			}
			words2 = append(words2, num)
		}
		list2 = append(list2, words2)
	}

	//for _, l := range list2 {
	//	fmt.Printf("list2: %v\n", l)
	//}
	//fmt.Println(len(list2))
	//fmt.Println(checkSum(list2))
	fmt.Println(checkSum2(list2))

}

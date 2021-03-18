package main

import (
	"fmt"
	"unsafe"
)


func main(){
	var k int
	num := []int{3,4,5}
	rem := []int{2,3,1}
	k = int(unsafe.Sizeof(num)) / int(unsafe.Sizeof(num[0]))
	fmt.Println("X is ", findMixX(num,rem,k))
}

func findMixX(num []int ,rem []int, k int) int{
	x :=1

	for (true){
		var j int
		for j=0;j<k;j++{
			if x%num[j] != rem[j]{
				break
			}
		}
		if j ==k {
			return x
		}
		x++
	}
	return x
}
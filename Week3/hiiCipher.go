package main

import (
	"errors"
	"fmt"
	"log"
)

func main() {
	var plainText string
	var encryptionKey string

	// Get the plain text from user
	fmt.Print("Enter the meesage you want to encrypt: ")
	fmt.Scanln(&plainText)

	// Validate the input
	plainTextRune := []rune(plainText)
	for _, v := range plainTextRune {
		if !IsAlphaBet(v) {
			log.Fatal("Invalid plaintext, must contain only alphabets")
		}
	}

	//Get the encrytion key from the user
	fmt.Print("\nEnter the encryption key of length ", len(plainText)*len(plainText), ": ")
	fmt.Scanln(&encryptionKey)

	// Validate the input
	if len(encryptionKey) != len(plainText)*len(plainText) {
		log.Fatal("ERROR: Encrytion key must be of length : ", len(plainText)*len(plainText))
	}
	encryptionKeyRune := []rune(encryptionKey)
	for _, v := range encryptionKeyRune {
		if !IsAlphaBet(v) {
			log.Fatal("Invalid encrytion key, must contain only alphabets")
		}
	}

	// create a n x 1 matrix to store the plaintext
	plainTextMatrix := make([][]int, len(plainText))
	for i, _ := range plainTextMatrix {
		plainTextMatrix[i] = make([]int, 1)
	}

	// Store the plaintext in the n x 1 matrix
	position := 0
	for i := 0; i < len(plainText); i++ {
		plainTextMatrix[i][0], _ = CharToNumber(plainTextRune[position])
		position++
	}

	// create a n x n matrix to store the encrytion key
	cipherTextMatrix := make([][]int, len(plainText))
	for i, _ := range cipherTextMatrix {
		cipherTextMatrix[i] = make([]int, len(plainText))
	}

	// Store the encryption key into the n x n matrix
	position = 0
	for i := 0; i < len(plainText); i++ {
		for j := 0; j < len(plainText); j++ {
			cipherTextMatrix[i][j], _ = CharToNumber(encryptionKeyRune[position])
			position++
		}
	}

	// create a n x 1 matrix to store the result
	encryptMatrix := make([][]int, len(plainText))
	for i, _ := range plainTextMatrix {
		encryptMatrix[i] = make([]int, 1)
	}

	// Perform matrix multiplication
	var result int
	for i := 0; i < len(plainText); i++ {
		for j := 0; j < len(plainText); j++ {
			// fmt.Println(cipherTextMatrix[i][j], plainTextMatrix[j][0])
			// fmt.Println(cipherTextMatrix[i][j] * plainTextMatrix[j][0])
			result += (cipherTextMatrix[i][j] * plainTextMatrix[j][0])
		}
		encryptMatrix[i][0] =result %26
		result = 0
	}

	// Print Encrypted matrix
	fmt.Println("Encrypteed message: ")
	for _, v := range encryptMatrix{
		for _, w := range v{
			fmt.Println("|",w,"|")
		}
	}

	fmt.Println(encryptMatrix)

}

func CharToNumber(r rune) (int, error) {
	if int(r) >= 65 && int(r) <= 90 {
		return int(r) - 65, nil
	}
	if int(r) >= 97 && int(r) <= 122 {
		return int(r) - 97, nil
	}
	return 0, errors.New("Not an alphabet")
}

// Function to check if an ASCII char is a valid english alphabet
func IsAlphaBet(r rune) bool {
	if (int(r) >= 65 && int(r) <= 90) || (int(r) >= 97 && int(r) <= 122) {
		return true
	}
	return false
}

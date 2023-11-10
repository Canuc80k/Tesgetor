#  Tesgetor
![](https://img.shields.io/badge/java-1.8%2B-orange) ![](https://img.shields.io/badge/window-7%2B-blue)

# Contributor
Thanks, Sir [@CarlavierVN](https://github.com/CarlavierVN), for helping as an unpaid QC and reporting issues. 


<br/>

# Feature
	- Compact tool helps you quickly create testcases for your competitive programming problems
	- Generate testcase using your own custom input and output file
	- Support multiple languages and systems
	- Friendly user interface
	
<br/>

Tesgetor will run your _input generator file_ and _output generator file_ multiple times to create testcases.
We have two main purposes:


# Purpose
	- Supports newbies in creating usable testcases without interacting with the console line
	- Fast and compact tool for small and medium programming competitions

	
![](https://i.ibb.co/vxKSpnr/a.png)

#  Setup

## Step 1: Download and setup Java

1. Go to [Java official website](https://www.java.com/en/download/ "java.com") and download lastest Java edition.
2. Setup Java by click to installer and spam "Yes" or "I agree"
3. Add Java Runtime Environment Path. You can follow [this tutorial video](https://www.youtube.com/watch?v=LnuUB-jydyU&t=30s "") from 0:30

<br/>

## Step 2: Add path for MinGW 
1. Download MinGW (which supports C++ 11 or above). Skip this if you already have Codeblock or Dev-C.
2. Add MinGW Enviroment Path. You can follow [this tutorial video](https://www.youtube.com/watch?v=mQra00mT3Dg)
3. Restart your computer to make sure everything is setup perfectly

<br/>

## Step 3: Config
1. Choose your input generator file, ex: [this file](https://paste.ubuntu.com/p/Fgp97tYPwS/)
2. Choose your output generator file ex: [this file](https://paste.ubuntu.com/p/gtPtN8QfBR/)
3. Choose your testcase output folder, this folder should be empty

![](https://i.ibb.co/1MQhHvx/Untitled1.png)
  
<br/>

## Step 4: Change generator file a little bit

1. Add command line argument: Instead of int main(), we do ```int main(int argc, char** argv)```
2. Add freopen in input generator: ```freopen(argv[1], "w", stdout);```
3. Add freopen in output generator: ```freopen(argv[1], "r", stdin); freopen(argv[2], "w", stdout);```
4. Use random function in C++ 11: high_resolution_clock instead of srand

Take a look at our example files in step 3 for better understanding.

<br/>

## Step 5: Generate testcase

1. If you want generate test from 1 -> 10
	+ If you fill [From = 1, To = 10], testcases will be: 1.INP, 1.OUT, 2.INP, 2.OUT, ..., 10.INP, 10.OUT
	+ If you fill [From = 01, To = 10], testcases will be: 01.INP, 01.OUT, 02.INP, 02.OUT, ..., 10.INP, 10.OUT
	+ If you fill [From = 001, To = 010], testcases will be: 001.INP, 001.OUT, 002.INP, 002.OUT, ..., 010.INP, 010.OUT
2. Timeout value means maximum time your generator files can run
	+ If your input generator runs in 1s for 1 test, output generator runs in 1s for 1 test. Your timeout should be >= 1
	
<br/>

## [Common Q & A](https://docs.google.com/document/d/1w9JIjzzNQg1ZDh0nMADl1f9dTTImvJUBrhCzo2S_EGM/edit?usp=sharing)

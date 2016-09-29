jar  = pascal2016.jar

#flag = -logS -testscanner
flag = -testparser

#part = scanner
part = parser

# Note: you can change the file path by doing: make file=tests/{file}.pas in terminal
file  = tests/mini.pas
files = tests/10star.pas tests/easter.pas tests/gcd.pas tests/mini.pas tests/opers.pas tests/primes.pas

all: clean compile runOne script
run: clean compile runAll script

compile:
	ant

script:
	bash folder.sh $(part)

runOne:
	java -jar $(jar) $(flag) $(file)

runAll:
	$(foreach f, $(files), java -jar $(jar) $(flag) $(f);)

clean:
	rm -f tests/*.log fails/*.log
	rm -f pascal2016.jar
	rm -rf tests/scanner_log

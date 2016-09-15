flag = -logS -testscanner
jar  = pascal2016.jar
part = testscanner

# Note: you can change the file path by doing: make file=tests/*.pas in terminal
file = tests/mini.pas
files = tests/10star.pas tests/easter.pas tests/gcd.pas tests/mini.pas tests/opers.pas tests/primes.pas

all: clean compile runOne script
run: clean compile loop script

compile:
	ant

runOne:
	java -jar $(jar) $(flag) $(file)

loop:
	$(foreach f, $(files), java -jar $(jar) $(flag) $(f);)

script:
	bash folder.sh $(part)

clean:
	rm -f tests/*.log fails/*.log
	rm -f pascal2016.jar
	rm -rf tests/scanner_log

jar  = pascal2016.jar

#flag = -testscanner
#flag = -testparser
flag = -testchecker

#part = scanner
#part = parser
part = checker

# Note: you can change the file path by doing: make file=tests/{file}.pas in terminal
file  = tests/primes.pas
files = tests/10star.pas tests/easter.pas tests/gcd.pas tests/mini.pas tests/opers.pas tests/primes.pas

all: clean compile runOne script
run: clean compile runAll script
zip: clean deploy

compile:
	ant

deploy:
	ant zip

script:
	bash folder.sh $(part)

runOne:
	java -jar $(jar) $(flag) $(file)

runAll:
	$(foreach f, $(files), java -jar $(jar) $(flag) $(f);)

clean:
	rm -f tests/*.log fails/*.log
	rm -f pascal2016.jar
	rm -rf tests/checker_log
	rm -rf classes

flag = -logS -testscanner
jar  = pascal2016.jar

# Note: you can change the file path by doing: make file=tests/*.pas in terminal
file = tests/mini.pas
files = tests/10star.pas tests/easter.pas tests/gcd.pas tests/mini.pas tests/opers.pas tests/primes.pas

all: cleanJar compile runOne

compile:
	ant

runOne:
	java -jar $(jar) $(flag) $(file)

run: cleanLog cleanJar compile
	$(foreach f, $(files), java -jar $(jar) $(flag) $(f);)

cleanLog:
	rm -f tests/*.log fails/*.log

cleanJar:
	rm -f pascal2016.jar

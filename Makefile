cc 	 = ant
flag = -logS -testscanner
jar  = pascal2016.jar
file = fails/kommentar-test.pas
# tests/mini.pas

all: clean compile run

compile:
	ant

run:
	java -jar $(jar) $(flag) $(file)

clean:
	rm -f pascal2016.jar

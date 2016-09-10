cc 	 = ant
flag = -logS -testscanner
jar  = pascal2016.jar
file = fails/ulovlig_tegn.pas

all: clean compile run

compile:
	ant

run:
	java -jar $(jar) $(flag) $(file)

clean:
	rm -f pascal2016.jar

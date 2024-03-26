test: #A build of the app
	./gradlew test

file1 := filepath1
file2 := filepath2
run-dist: #Launch of the program
	./build/install/app/bin/app $(file1) $(file2)

build: #A build of the app
	./gradlew installDist

clean:
	./gradlew clean

lint: #Chech a style of code via Checkstyle
	./gradlew checkstyleMain

report: #Make a JaCoCo Report
	./gradlew jacocoTestReport

.PHONY: build

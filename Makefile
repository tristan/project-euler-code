CXXFLAGS=-std=c++11 -O3 -g3
CFLAGS=-std=c99 -lm
JAVACFLAGS=-Xlint:unchecked
PYTHON=python2

PROB=0020

cpp: ${PROB}.cpp
	@${CXX} ${CXXFLAGS} benchmark.cpp ${PROB}.cpp -o ${PROB}
	@./${PROB}

c: ${PROB}.c
	@${CC} ${CFLAGS} benchmark.c ${PROB}.c -o ${PROB}
	@./${PROB}

clj: ${PROB}.clj
	@clj benchmark.clj ${PROB}

py: ${PROB}.py
	@cp ${PROB}.py euler__.py
	@${PYTHON} benchmark.py
	@rm euler__.py

java: ${PROB}.java
	@cp ${PROB}.java _${PROB}.java
	@javac ${JAVACFLAGS} Benchmark.java _${PROB}.java
	@rm _${PROB}.java
	@java Benchmark ${PROB}

all:
	@for ext in c cpp clj py java; do \
	if [ -f ${PROB}.$$ext ]; then \
	make -s $$ext; \
	fi; done

clean:
	find . -regex "^./[0-9]+$$" -exec rm {} \;
	rm *.pyc *.class *~

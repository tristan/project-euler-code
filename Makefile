CXXFLAGS=-std=c++11 -O3 -g3
PYTHON=python2
PROB=0001

cpp:
	@${CXX} ${CXXFLAGS} benchmark.cpp ${PROB}.cpp -o ${PROB}
	@./${PROB}

clj:
	@clj benchmark.clj ${PROB}

py:
	@cp ${PROB}.py euler__.py
	@${PYTHON} benchmark.py
	@rm euler__.py

java:
	@cp ${PROB}.java _${PROB}.java
	@javac Benchmark.java _${PROB}.java
	@rm _${PROB}.java
	@java Benchmark ${PROB}

clean:
	find . -regex "^./[0-9]+$$" -exec rm {} \;
	rm *.pyc
	rm *~
	rm *.class

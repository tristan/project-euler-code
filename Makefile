CXXFLAGS=-std=c++11 -O3 -g3

PROB=0037

prob:
	${CXX} ${CXXFLAGS} benchmark.cpp ${PROB}.cpp -o ${PROB}
	./${PROB}

oldprob:
	${CXX} ${CXXFLAGS} ${PROB}.cpp -o ${PROB}
	./${PROB}

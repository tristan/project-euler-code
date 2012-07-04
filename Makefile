CXXFLAGS=-std=c++11 -O3 -g3

PROB=0018

prob:
	${CXX} ${CXXFLAGS} ${PROB}.cpp -o ${PROB}
	./${PROB}

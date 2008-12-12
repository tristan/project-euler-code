if __name__ == '__main__':
    i = 20
    result = 0
    fail = True
    highno = 2
    while fail:
        fail = False
        for j in range(3, 21):
            #print 'Trying', i, '%', j
            if i % j != 0:
                fail = True
                break
            if highno < j:
                print 'new high no', j
                highno = j
        if not fail:
            print i
        else:
            i += 1
            
        

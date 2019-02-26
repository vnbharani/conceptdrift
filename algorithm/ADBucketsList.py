
class ADBucketsList(object):
    """docstring for ADBucketsList."""
    def __init__(self,M=5, head=None):
        super(ADBucketsList, self).__init__()
        self.head = head
        self.M=M
        if self.head is None:
            self.head = ADBuckets(M+1,None,None)


    def addAnInput(self, input):
        newBucket = ADBucket(1,value)
        self.head.buckets[head.count]= newBucket
        self.head.sum +=value
        self.head.count+=1
        checkForCompression(head)

    def addInputAtNextBucket(self,curr,next,value):
        if next is None:
            next = ADBucket(self.M+1,curr,None)

        next.buckets[next.count] = value
        next.sum+=value.content
        next.count+=1


    def checkForCompression(self,head):
        if head is None:
            return

        if head.count is M+1:
            b0=head.buckets[0]
            b1=head.buckets[1]

            compressedBucket = ADBucket(0,0.0)
            compressedBucket.content = b0.content + b1.content
            compressedBucket.capacity = bo.capacity+b1.capacity
            addInputAtNextBucket(head,head.next,compressedBucket)

            for i in range(M-2):
                head.buckets[i]= head.buckets[i+2]

            head.count-=2

        checkForCompression(head.next)


    def deleteLastBucket():
        curr = self.head
        while curr.next is not None:
            curr = curr.next

        deletedBucket = curr
        curr.prev.next = None
        return deletedBucket

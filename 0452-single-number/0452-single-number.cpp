class Solution {
public:
    int singleNumber(int A[], int n) {
        int j=0;
        for(int i=0;i<n;i++){
            j=j^A[i];
        }
        return j;
    }
};
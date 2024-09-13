class Solution {
    public int search(int[] nums, int target) {
        int l=0, r= nums.length-1;
        for(int i=0;i<nums.length;i++){
            int mid = (l+r)/2;
            if(target > nums[mid]){
                l = mid+1;
            }else if(target < nums[mid]){
                r = mid-1;
            }else if(target == nums[mid]) return mid;
        }
        return -1;
    }
    public  void main(String[] args) {
        int[] nums = {-1,0,2,4,6,8};
        System.out.println(search(nums, 4));
    }
}

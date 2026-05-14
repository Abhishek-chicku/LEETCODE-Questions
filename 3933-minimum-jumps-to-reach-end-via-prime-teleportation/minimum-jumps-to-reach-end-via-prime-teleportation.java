class Solution {
    public int minJumps(int[] nums) {
        int max=nums[0];
        for(int ele:nums){
            max=Math.max(max,ele);
        }
        int isPrime[]=new int[max+1];
        Arrays.fill(isPrime,1);
        isPrime[0]=0;
        isPrime[1]=0;
        for(int i=2;i*i<=max;i++){
            if(isPrime[i]==1){
                for(int j=2;i*j<=max;j++)isPrime[i*j]=0;
            }
        }

        
        HashMap<Integer,List<Integer>> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],new ArrayList<>());
                map.get(nums[i]).add(i);
            }
            else map.get(nums[i]).add(i);
        }

        Queue<Integer> q=new ArrayDeque<>();
        int steps=0;
        int visited[]=new int[nums.length];

        q.offer(0);
        visited[0]=1;
        HashSet<Integer> used=new HashSet<>();

        while(!q.isEmpty()){
            int size=q.size();
            while(size>0){
                int e=q.poll();
                if(e==nums.length-1)return steps;

                if(e-1>=0){
                    if(visited[e-1]==0){
                        q.offer(e-1);
                        visited[e-1]=1;
                    }
                    
                }

                if(e+1<nums.length){
                    if(visited[e+1]==0){
                        q.offer(e+1);
                        visited[e+1]=1;
                    }
                }

                if(isPrime[nums[e]]==1 && !used.contains(nums[e])){
                    for(int i=1;nums[e]*i<=max;i++){
                        if(map.containsKey(nums[e]*i)){
                            List<Integer> t=map.get(nums[e]*i);
                            for(int ele:t){
                                if(visited[ele]==0){
                                    q.offer(ele);
                                    visited[ele]=1;
                                }
                            }
                        }
                    }
                }
                used.add(nums[e]);

                size--;
            }
            steps++;
        }
        return -1;
    }
}
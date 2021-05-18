public class GeneralizedTowerOfHanoi{
    public static void gtoh_with_recursion(int num_disks, int start_pos, int r, int b){
        if(num_disks>0){ 
            int last =r;
            if(num_disks%2!=0)    last =b;                 // Sets the last to r or b depending on which is last disk
            int mid = 6-start_pos-last;                     
            if(start_pos == last)     mid= start_pos;      //mid is set as start if start and last are same, to avoid positions like 4
            gtoh_with_recursion(num_disks-1, start_pos, mid, mid);
            if(start_pos!=last)    System.out.println(start_pos+" "+last);          
            gtoh_with_recursion(num_disks-1, mid, r, b);
        }
    }
    public static void gtoh_without_recursion(int num_disks, int start_pos, int r, int b) {
        class track{                        // A class that has input parameters like the func.
            int num;                        // Will help keep track of the change in input
            int start;
            int red;
            int black;
            int stage;                      // Will store the stage of each input
            track(int n, int s, int re, int bl){
                num=n;
                start = s;
                red = re;
                black = bl;
            }
        }
        MyStack<track> stim = new MyStack<track>();
        track pre = new track(num_disks,start_pos,r,b);
        pre.stage=0;
        stim.push(pre);
        while(!stim.empty()){
            pre = stim.peek();                             // Will work on the data at top of the stack
            stim.pop();
            if(pre.num>0){
                switch(pre.stage){
                    case 0:                                 // Takes care of first recursive call
                        pre.stage =1;                        //Stage of the present one is changed to 1
                        stim.push(pre);
                        int last = pre.red;
                        if(pre.num%2!=0)    last = pre.black;
                        int mid = 6-pre.start-last;
                        if(pre.start==last)    mid = pre.start;
                        track fresh = new track(pre.num-1, pre.start, mid, mid);
                        fresh.stage =0;                         
                        stim.push(fresh);                        // A new data comes at the top with stage 0
                        break;
                    case 1:                                     // Executes the printing part 
                        pre.stage =2;                            // Updates the stage of current data to 2
                        stim.push(pre);
                        last = pre.red;
                        if(pre.num%2!=0)    last = pre.black;
                        if(pre.start!=last)   System.out.println(pre.start+" "+last);
                        break;
                    case 2:                                     // For the second recursive step
                        last = pre.red;
                        if(pre.num%2!=0)    last = pre.black;
                        mid = 6-pre.start-last;
                        if(pre.start==last)    mid = pre.start;
                        track fresher = new track(pre.num-1,mid,pre.red,pre.black);
                        fresher.stage=0;
                        stim.push(fresher);                      // A new data comes at the top with stage 0
                        break;
                }
            }
        }        
    }    
    public static void main(String[] args) {
        
    }
}
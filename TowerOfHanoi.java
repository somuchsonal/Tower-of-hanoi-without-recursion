public class TowerOfHanoi{
    public static void toh_with_recursion(int num_disks, int start_pos,int end_pos){
        if(num_disks<1|| start_pos==end_pos) return;                                //Nothing happens for these 2 cases
        else {
            toh_with_recursion(num_disks-1 , start_pos , 6-start_pos-end_pos);      // 6-start-end gives the value of the empty rod
            System.out.println(start_pos+" "+end_pos);
            toh_with_recursion(num_disks-1 , 6-start_pos-end_pos , end_pos);
        }
    }
    public static void toh_without_recursion(int num_disks, int start_pos, int end_pos){
        if(start_pos==end_pos) return;
        class track{                                   // A class that has input parameters like the func.
            int n;                                     // Will help keep track of the change in input
            int start;
            int end;
            int stage;                                  // Will store the stage of each input
            track(int num,int s, int e){
                n = num;
                start = s;
                end = e;
            }
        }
        MyStack<track> stim = new MyStack<track>();
        track pre = new track(num_disks,start_pos,end_pos);
        pre.stage = 0;
        stim.push(pre);
        while(!stim.empty()){
            pre = stim.peek();                          // Will work on the data at top of the stack
            stim.pop();
            if(pre.n>0){
                switch(pre.stage){
                    case 0:                                 // Takes care of first recursive call
                        pre.stage=1;                        //Stage of the present one is changed to 1
                        stim.push(pre);
                        track fresh = new track(pre.n-1, pre.start, 6-pre.start-pre.end);
                        fresh.stage = 0;
                        stim.push(fresh);                   // A new data comes at the top with stage 0
                        break;
                    case 1:                                  // Executes the printing part   
                        pre.stage =2;                         // Updates the stage of current data to 2
                        stim.push(pre);
                        System.out.println(pre.start+" "+pre.end);
                        break;      
                    case 2 :                                // For the second recursive step
                        track fresher = new track(pre.n-1,6-pre.start-pre.end,pre.end);
                        fresher.stage=0;
                        stim.push(fresher);                  // A new data comes at the top with stage 0
                        break;
                }
            }
        }
    }
    public static void main(String[] args) {
        
    }
}
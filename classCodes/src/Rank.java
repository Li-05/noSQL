import java.util.Arrays;
import java.util.List;

public class Rank {

    public static void swap(String[] input,int i,int j){
        String con=input[i];
        input[i]=input[j];
        input[j]=con;
    }

    public static String[] reRank(String[] input){
        if(input==null || input.length==0){
            return new String[0];
        }
        int i=0;
        int left=0;
        int right=input.length-1;
        while(i<=right){
            if(input[i].equals("r")){
                swap(input,i,left);
                i++;
                left++;
            }else if(input[i].equals("g")){
                i++;
            }else{
                swap(input,i,right);
                right--;
            }
        }
        return input;
    }

    public static void main(String []avgs){
        String[] input=new String[]{"r","r","b","g","b","r","g"};
        Arrays.asList(reRank(input)).forEach(e->System.out.print(e));
    }
}

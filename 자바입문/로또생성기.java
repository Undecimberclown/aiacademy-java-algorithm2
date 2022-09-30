package 자바입문;
import java.util.*;
/*
 * Lotto1

로또번호 생성기를 만들 거다

조건)

1. 서로 겹치지 않는 숫자 6개를 생성

2. 각 숫자는 1 ~ 45 범위 내의 숫자

3. 매번 실행 시 다른 숫자 출력

4. 오름차순 정렬

-> 랜덤수 : Math 사용

입력은 없다.
 

결과)

******************************************************************
로또 추첨 결과
******************************************************************
2 11 25 27 35 38
******************************************************************


 */


// 중복 체크하는 단계

class 로또생성기 {
    public static void main(String[] args) {
        로또생성기 solution = new 로또생성기();
        System.out.println(solution.solution());
        
    }
    public String solution(){
        String title = " ### 로또 ### \n";
        

        int[] myLotto = buyLotto();

       
        System.out.println("=== 로또 추첨 ===");
        
        int[] arr = new int[6];
        Random random = new Random();
        for(int i =0; i< 6; i++ ){
            int number = random.nextInt(8) + 1;
            // 로또번호 중복체크
            boolean check = false; // false가 중복되지 않은 값
            check = contains(arr, number, check);
            if(check==false){
                arr[i] = number; // 중복되지 않았으니 배열에 담아라.
            }else{
                i--; // 중복됐으면 이번 회수는 무효로 하고 다시 뽑아라.
            }
        }
        arr = sort(arr);
        int count = check(myLotto, arr);
        String answer = printArray(arr);
        
        return title + answer + rank(count);
    }
    /**
     * 배열 출력 메소드
    */
    public String printArray(int[] arr){
        String result = "";
        for(int i = 0; i< arr.length; i++){
            result += arr[i]+"\t";
        }
        String answer = String.format(
        "******************************************\n"
        +"로또 번호\n"
        +"******************************************\n"
        +"%s"
        +"\n******************************************\n", result);
        return answer;
    }
    /** 
     * 중복체크 메서드
    */
    public boolean contains(int[] arr, int number, boolean check){
        for(int j=0; j<6; j++){
            if(arr[j] == number){
                check = true;
            }
        }
        return check;
        
    }
    /**
     * 버블정렬
     */
    public int[] sort(int[] arr){
        for (int j = 0; j < arr.length; j++){
            for( int i = 0; i < arr.length -1; i++){
                if(arr[i] > arr[i+1]){
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
        return arr;
    }
    /**
     * 숫자 추출
     */
    public int[] buyLotto(){
        System.out.println("=== 로또 구매 ===");
        int[] myLotto =new int[6];
        for(int i =0; i < 6; i++){
            System.out.println("원하는 숫자를 입력하시오. 단, 범위는 1부터 8까지 입니다.");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            System.out.println(String.format("당신이 입력한 번호는 %d입니다.", input));
            myLotto[i] = input;
            
        }
        String answer = printArray(myLotto);
        System.out.println(answer);
        return myLotto;
    }
    public int check(int arr[], int com[]){
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < com.length; j++ ){
                if(arr[i]==com[j]){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     *  로또 등수 정하기
     *  1등 : 6개 다 맞춤
     *  2등 : 5개 맞춤
     *  3등 : 4개 맞춤
     *  4등 : 3개 맞춤
     *  이하는 꽝
     */
    public String rank(int count){
        String message = "";
        switch(count){
            case 3:
            message = "4등";
            break;
            case 4:
            message = "3등";
            break;
            case 5:
            message = "2등.";
            break;
            case 6:
            message = "1등";
            break;
            default:
            message = "꽝";
            break;

        }
        String prefix = (!message.equals("꽝")) ? "축하합니다!! " : "아쉽네요. 다음기회에... ";
        return prefix + message;


    }
    
}

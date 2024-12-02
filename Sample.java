import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Sample{
    public static void title(){
        System.out.println("<BlackJack Game>");
        System.out.println("PUSH ENTER KEY");
    }

    public static Integer draw(ArrayList<Integer> cards, Integer n){
        Integer ret = cards.get(n);
        cards.remove((int)(n));
        return ret;
    }
    
    public static Integer calc_point(ArrayList<Integer> board){
        Integer n =0;
        for(Integer i = 0 ; i <board.size() ;i++){
            n += board.get(i);
        }
        return n;
    }

    public static String print_board(ArrayList<Integer> player_board, ArrayList<Integer> dealer_board, Scanner sc){
        System.out.println("============");
        System.err.println("Dealer:[" + dealer_board.get(0) + ", ??]");
        System.out.println("Player:" + player_board);
        System.out.println("Your point -> " + calc_point(player_board));
        System.out.println();
        if(calc_point(player_board) > 21){
            System.out.println("Your lose");
            System.exit(0);
        }
        System.out.print("1:draw 2:stand =>");
        String ret = sc.nextLine();
        return ret;
    }

    public static void print_board(ArrayList<Integer> player_board, ArrayList<Integer> dealer_board){
        System.out.println("==========");
        System.out.println("Dealer's point ->" + calc_point(dealer_board));
        System.out.println("dealer:" + dealer_board);
        System.out.println("Player:" + player_board);
        System.out.println("Your point -> " + calc_point(player_board));
        System.out.println();
        if(calc_point(dealer_board) < 17){
            System.out.println("Dealer draws one more");
        }
        else if(calc_point(dealer_board) > 21){
            System.out.println("Dealer is burst. YOU WIN!");
            System.exit(0);
        }
    }
    
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        title();
        sc.nextLine();
        // トランプを作る
        // 1 / 4 =0 になる。javaは小数点が破棄される。
        // ブラックジャックはJ〜Kは１０とする、今回は特別でAは1とする
        ArrayList<Integer> cards = new ArrayList<>();
        for(Integer i = 0; i < 52 ;i++){
            Integer num = i / 4 + 1;
            if(num > 10){
                cards.add(10);
            }
            else{
                cards.add(num);
            }
        }

        // トランプをシャッフルする。
        Collections.shuffle(cards);

        ArrayList<Integer> player_board = new ArrayList<>();
        ArrayList<Integer> dealer_board = new ArrayList<>();

        //deal cards
        player_board.add(draw(cards, 0));
        player_board.add(draw(cards, 0));
        dealer_board.add(draw(cards, 0));
        dealer_board.add(draw(cards, 0));

        while(true){
            String c = print_board(player_board, dealer_board, sc);
            if(c.equals("1")){
                player_board.add(draw(cards, 0));
            }
            else if(c.equals("2")){
                break;
            }
        }
        //ディーラーのターン
        
        while(true){
            print_board(player_board, dealer_board);
            if(calc_point(dealer_board) < 17){
               sc.nextLine();
               dealer_board.add(draw(cards, 0));
            }
            else{
                break;
            }
        }
        // 勝敗判定
        if(calc_point(player_board) > calc_point(dealer_board)){
            System.out.println("YOU WIN!!");
        }
        else if(calc_point(player_board) < calc_point(dealer_board)){
            System.out.println("YOU LOSE...");
        }
        else{
            System.out.println("Draw...  play more!!");
        }
        }
    }

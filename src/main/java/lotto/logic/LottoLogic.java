package lotto.logic;

import lotto.model.Lotto;
import lotto.view.View;

import java.util.ArrayList;
import java.util.List;

public class LottoLogic implements Logic {
    private final View view;
    private boolean running = true;
    private List<Lotto> lotteries = new ArrayList<>();

    public LottoLogic(View view) {
        this.view = view;
    }

    @Override
    public void start() {
        while (running) {
            run();
        }
    }

    @Override
    public void run() {
        int payment = getPurchaseAmount();
    }

    private int getPurchaseAmount() {
        int payment = 0;
        while (payment == 0) {
            try {
                String input = view.getPurchaseAmount();
                payment = validPayment(input);
            } catch (IllegalArgumentException e) {
                view.printError(e.getMessage());
            }
        }
        return payment;
    }

    private int validPayment(String input) {
        int payment;
        try {
            payment = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("지불 금액은 정수여야 합니다.");
        }
        if (payment <= 0) {
            throw new IllegalArgumentException("지불 금액은 0 보다 커야 합니다.");
        } else if (payment % 1000 != 0) {
            throw new IllegalArgumentException("지불 금액은 1000원 단위여야 합니다.");
        }
        return payment;
    }

}

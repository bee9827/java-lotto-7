package lotto;

import lotto.controller.Controller;
import lotto.service.LottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoMachine lottoMachine = new LottoMachine();
        Controller controller = new Controller(inputView, outputView, lottoMachine);

        controller.run();
    }
}

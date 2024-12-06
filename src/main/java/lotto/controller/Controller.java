package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.service.LottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public Controller(InputView inputView, OutputView outputView, LottoMachine lottoMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }


    public void run(){
        outputView.printInstruction("구입금액을 입력해 주세요.");
        Integer money = inputView.askPrice();
        outputView.printLineSeparator();

        lottoMachine.buyLotto(money);
        outputView.printList(lottoMachine.lottosToString());
        outputView.printLineSeparator();

        outputView.printInstruction("당첨 번호를 입력해 주세요.");
        List<Integer> winningNumbers = (inputView.askWinningNumbers());

        outputView.printInstruction("보너스 번호를 입력해 주세요.");
        Integer bonusNumber = inputView.askBonusNumber();

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        lottoMachine.setWinningLotto(winningLotto);

        outputView.printStatus(lottoMachine.getRankWithCount());
        outputView.printTotalProfit((long)money,lottoMachine.getTotalPrize());
    }

}

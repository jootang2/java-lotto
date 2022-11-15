package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class Service {

    public String getMoneyInput() {
        String input = Console.readLine();
        String moneyInput = input.replaceAll("\\s", "");
        checkMoney(moneyInput);
        return moneyInput;
    }

    private void checkMoney(String moneyInput) {
        try {
            int money = Integer.parseInt(moneyInput);
            if (!(money % 1000 == 0)) {
                throw new IllegalArgumentException("[ERROR] 1000원 단위로 입력해주세요.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해주세요.");
        }
    }

    public List<List<Integer>> createNumber(String moneyInput) {
        int money = Integer.parseInt(moneyInput);
        int numberOfCreate = money / 1000;
        List<List<Integer>> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < numberOfCreate; i++) {
            List<Integer> lottoNumber = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Lotto validate = new Lotto(lottoNumber);
            System.out.println(lottoNumber);
            lottoNumbers.add(lottoNumber);
        }

        return lottoNumbers;
    }

    public String getWinningNumberInput() {
        String input = Console.readLine();
        String winningNumberInput = input.replaceAll("\\s", "");
        checkWinningNumber0(winningNumberInput);
        return winningNumberInput;
    }

    public void checkWinningNumber0(String winningNumberInput) {
        if (!winningNumberInput.contains(",")) {
            throw new IllegalArgumentException("[ERROR] 올바른 형식이 아닙니다.");
        }
        String[] winningNumbers = winningNumberInput.split(",");
        if (winningNumbers.length != 6) {
            throw new IllegalArgumentException("[ERROR] 6개의 숫자를 입력해주세요.");
        }
        Set<String> winningNumberSet = new HashSet<>(Arrays.asList(winningNumbers));
        if (winningNumberSet.size() != winningNumbers.length) {
            throw new IllegalArgumentException("[ERROR] 중복된 숫자는 입력할 수 없습니다.");
        }
        checkWinningNumber1(winningNumbers);
    }

    public void checkWinningNumber1(String[] winningNumbers) {
        for (String winningNumber : winningNumbers) {
            try {
                Integer.parseInt(winningNumber);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 숫자를 입력해주세요.");
            }
            if (!(Integer.parseInt(winningNumber) > 0) || !(Integer.parseInt(winningNumber) < 46)) {
                throw new IllegalArgumentException("[ERROR] 범위를 벗어나는 숫자입니다.");
            }
        }
    }

    public String getLuckyNumberInput(String winningNumberInput) {
        String input = Console.readLine();
        String luckyNumberInput = input.replaceAll("\\s", "");
        checkLuckyNumber0(luckyNumberInput, winningNumberInput);
        return luckyNumberInput;
    }

    public void checkLuckyNumber0(String luckyNumberInput, String winningNumberInput) {
        try {
            int luckyNumber = Integer.parseInt(luckyNumberInput);
            if (!(luckyNumber > 0) || !(luckyNumber < 46)) {
                throw new IllegalArgumentException("[ERROR] 범위를 벗어나는 숫자입니다.");
            }
            checkLuckyNumber1(luckyNumber, winningNumberInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해주세요.");
        }
    }

    public void checkLuckyNumber1(int luckyNumber, String winningNumberInput) {
        String[] winningNumbers = winningNumberInput.split(",");
        for (String winningNumber : winningNumbers) {
            if (luckyNumber == Integer.parseInt(winningNumber)) {
                throw new IllegalArgumentException("[ERROR] 중복된 숫자는 입력할 수 없습니다.");
            }
        }
    }
}

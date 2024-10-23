package com.pravo.pravo.domain.member.service;

import com.pravo.pravo.domain.member.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNameGenerator {

    private final MemberRepository memberRepository;
    private final Random random;
    private static final List<String> ADJECTIVES = List.of("똑똑한", "예쁜", "재미있는", "착한", "용감한");
    private static final List<String> FRUITS = List.of("망고", "아보카도", "사과", "바나나", "배");

    public RandomNameGenerator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.random = new Random();
    }

    private List<String> generateCombinedNames() {
        List<String> combinedNames = new ArrayList<>();
        for (String adjective : ADJECTIVES) {
            for (String fruit : FRUITS) {
                combinedNames.add(adjective + " " + fruit);
            }
        }
        return combinedNames;
    }

    public String generateUniqueRandomName() {
        List<String> combinedNames = generateCombinedNames();
        String randomName;
        int count = 1;

        do {
            randomName = combinedNames.get(
                random.nextInt(combinedNames.size())); // random name in combinedNames list

            while (memberRepository.existsByName(randomName)) {
                randomName = combinedNames.get(random.nextInt(combinedNames.size())) + count;
                count++;
            }
        } while (memberRepository.existsByName(randomName));

        return randomName;
    }
}
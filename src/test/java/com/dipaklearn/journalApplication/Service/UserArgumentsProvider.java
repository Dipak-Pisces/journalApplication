package com.dipaklearn.journalApplication.Service;

import com.dipaklearn.journalApplication.Entity.UserData;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;


public class UserArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(UserData.builder().username("shweta").password("shweta").build()),
                Arguments.of(UserData.builder().username("shubha").password("shubha").build())
        );
    }
}

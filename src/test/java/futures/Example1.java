package futures;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Example1 {

    @Test
    void name() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread());
            return "abc";
        });
        System.out.println(future.get());
        System.out.println(future.isDone());
    }

    @Test
    void complete() {
        CompletableFuture<Integer> future = new CompletableFuture<>();

        assertThat(future.isDone(), is(false));

        //future.complete(42);
        future.completeExceptionally(new IllegalStateException());

        assertThat(future.isDone(), is(true));
        assertThat(future.isCompletedExceptionally(), is(true));
        assertThrows(CompletionException.class, future::join);
        //assertThat(future.join(), is(42));
    }

    @Test
    void supplyAsync() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 123);

        future.join();
        assertThat(future.isDone(), is(true));
        assertThat(future.join(), is(123));
    }

    @Test
    void thenAccept() {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> 123)
                .thenAccept(System.out::println);
        //assertThat(future.isDone(), is(true));
        //assertThat(future.join(), is(123));
    }

    @Test
    void combine() {
        CompletableFuture.supplyAsync(() -> "21")
                .thenApply(Integer::valueOf)
                .thenCombine(CompletableFuture.supplyAsync(() -> 21), Integer::sum)
                .thenAccept(System.out::println)
                .join();
    }

    @Test
    void exceptionally() {
        CompletableFuture.supplyAsync(() -> {
                    if (true) {
                        throw new IllegalStateException();
                    }
                    return 123;
                })
                .exceptionally(throwable -> 42)
                .thenAccept(System.out::println).join();
    }
}

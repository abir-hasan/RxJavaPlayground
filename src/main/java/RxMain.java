import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

import java.util.concurrent.TimeUnit;

/**
 * Created by Abir Hasan123 on 07-May-17.
 */
public class RxMain {

    public static void main(String[] args) {
        commonSubjectExample();
        replaySubjectExample();
        replySubjectWithFixedBuffer();
        replySubjectWithFixedTime();
    }

    private static void replySubjectWithFixedTime() {
        ReplaySubject<Integer> s = ReplaySubject.createWithTime(150, TimeUnit.MILLISECONDS,
                Schedulers.trampoline());
        s.onNext(0);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s.onNext(1);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s.onNext(2);
        s.onNext(3);
        s.subscribe(v -> System.out.println("Late: " + v));
        s.onNext(4);
    }

    private static void replySubjectWithFixedBuffer() {
        ReplaySubject<Integer> rs = ReplaySubject.createWithSize(2);
        rs.onNext(1);
        rs.onNext(2);
        rs.onNext(3);
        rs.onNext(4);
        rs.subscribe(v -> System.out.println("fixed buffer: " + v));
    }

    /**
     * Subject with special feature of caching
     * see the output
     */
    private static void replaySubjectExample() {
        ReplaySubject<Integer> replaySubject = ReplaySubject.create();
        replaySubject.subscribe(v -> System.out.println("1st: " + v));
        replaySubject.onNext(1);
        replaySubject.onNext(2);
        replaySubject.onNext(3);
        replaySubject.subscribe(v -> System.out.println("2nd: " + v));
        replaySubject.onNext(4);
        replaySubject.onNext(5);
    }

    /**
     * Most common example of Subject which extends from Observable
     */
    private static void commonSubjectExample() {
        PublishSubject<Integer> subject = PublishSubject.create();
        subject.onNext(1);
        subject.subscribe(System.out::println);
        subject.onNext(2);
        subject.onNext(3);
        subject.onNext(4);
        subject.onNext(5);
    }
}

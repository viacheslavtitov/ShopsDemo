package globus.shops.demo.app.domain.base;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public abstract class BaseInteractor<ResultType, ParameterType> {

    private final CompositeDisposable subscription = new CompositeDisposable();
    protected final Scheduler jobScheduler;
    private final Scheduler uiScheduler;

    public BaseInteractor(Scheduler jobScheduler, Scheduler uiScheduler) {
        this.jobScheduler = jobScheduler;
        this.uiScheduler = uiScheduler;
    }

    protected abstract Observable<ResultType> buildObservable(ParameterType parameter);

    public void execute(ParameterType parameter,
                        Consumer<ResultType> subscriber,
                        Consumer<Throwable> errorSubscriber,
                        Action completeSubscriber) {
        subscription.add(buildObservable(parameter)
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler)
                .subscribe(subscriber, errorSubscriber, completeSubscriber));
    }

    public void execute(Consumer<ResultType> subscriber,
                        Consumer<Throwable> errorSubscriber,
                        Action completeSubscriber) {
        execute(null, subscriber, errorSubscriber, completeSubscriber);
    }

    public void unsubscribe() {
        subscription.clear();
    }

}

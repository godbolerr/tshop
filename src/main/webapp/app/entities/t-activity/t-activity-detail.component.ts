import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { TActivity } from './t-activity.model';
import { TActivityService } from './t-activity.service';

@Component({
    selector: 'jhi-t-activity-detail',
    templateUrl: './t-activity-detail.component.html'
})
export class TActivityDetailComponent implements OnInit, OnDestroy {

    tActivity: TActivity;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tActivityService: TActivityService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTActivities();
    }

    load(id) {
        this.tActivityService.find(id).subscribe((tActivity) => {
            this.tActivity = tActivity;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTActivities() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tActivityListModification',
            (response) => this.load(this.tActivity.id)
        );
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { TResource } from './t-resource.model';
import { TResourceService } from './t-resource.service';

@Component({
    selector: 'jhi-t-resource-detail',
    templateUrl: './t-resource-detail.component.html'
})
export class TResourceDetailComponent implements OnInit, OnDestroy {

    tResource: TResource;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tResourceService: TResourceService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTResources();
    }

    load(id) {
        this.tResourceService.find(id).subscribe((tResource) => {
            this.tResource = tResource;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTResources() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tResourceListModification',
            (response) => this.load(this.tResource.id)
        );
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TActivity } from './t-activity.model';
import { TActivityPopupService } from './t-activity-popup.service';
import { TActivityService } from './t-activity.service';

@Component({
    selector: 'jhi-t-activity-delete-dialog',
    templateUrl: './t-activity-delete-dialog.component.html'
})
export class TActivityDeleteDialogComponent {

    tActivity: TActivity;

    constructor(
        private tActivityService: TActivityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.tActivityService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tActivityListModification',
                content: 'Deleted an tActivity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-t-activity-delete-popup',
    template: ''
})
export class TActivityDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tActivityPopupService: TActivityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.tActivityPopupService
                .open(TActivityDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

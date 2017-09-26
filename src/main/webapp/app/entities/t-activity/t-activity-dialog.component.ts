import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TActivity } from './t-activity.model';
import { TActivityPopupService } from './t-activity-popup.service';
import { TActivityService } from './t-activity.service';

@Component({
    selector: 'jhi-t-activity-dialog',
    templateUrl: './t-activity-dialog.component.html'
})
export class TActivityDialogComponent implements OnInit {

    tActivity: TActivity;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private tActivityService: TActivityService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.tActivity.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tActivityService.update(this.tActivity));
        } else {
            this.subscribeToSaveResponse(
                this.tActivityService.create(this.tActivity));
        }
    }

    private subscribeToSaveResponse(result: Observable<TActivity>) {
        result.subscribe((res: TActivity) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: TActivity) {
        this.eventManager.broadcast({ name: 'tActivityListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-t-activity-popup',
    template: ''
})
export class TActivityPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tActivityPopupService: TActivityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tActivityPopupService
                    .open(TActivityDialogComponent as Component, params['id']);
            } else {
                this.tActivityPopupService
                    .open(TActivityDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TResource } from './t-resource.model';
import { TResourcePopupService } from './t-resource-popup.service';
import { TResourceService } from './t-resource.service';

@Component({
    selector: 'jhi-t-resource-dialog',
    templateUrl: './t-resource-dialog.component.html'
})
export class TResourceDialogComponent implements OnInit {

    tResource: TResource;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private tResourceService: TResourceService,
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
        if (this.tResource.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tResourceService.update(this.tResource));
        } else {
            this.subscribeToSaveResponse(
                this.tResourceService.create(this.tResource));
        }
    }

    private subscribeToSaveResponse(result: Observable<TResource>) {
        result.subscribe((res: TResource) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: TResource) {
        this.eventManager.broadcast({ name: 'tResourceListModification', content: 'OK'});
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
    selector: 'jhi-t-resource-popup',
    template: ''
})
export class TResourcePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tResourcePopupService: TResourcePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tResourcePopupService
                    .open(TResourceDialogComponent as Component, params['id']);
            } else {
                this.tResourcePopupService
                    .open(TResourceDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

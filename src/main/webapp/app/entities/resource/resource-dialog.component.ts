import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Resource } from './resource.model';
import { ResourcePopupService } from './resource-popup.service';
import { ResourceService } from './resource.service';

@Component({
    selector: 'jhi-resource-dialog',
    templateUrl: './resource-dialog.component.html'
})
export class ResourceDialogComponent implements OnInit {

    resource: Resource;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private resourceService: ResourceService,
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
        if (this.resource.id !== undefined) {
            this.subscribeToSaveResponse(
                this.resourceService.update(this.resource));
        } else {
            this.subscribeToSaveResponse(
                this.resourceService.create(this.resource));
        }
    }

    private subscribeToSaveResponse(result: Observable<Resource>) {
        result.subscribe((res: Resource) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Resource) {
        this.eventManager.broadcast({ name: 'resourceListModification', content: 'OK'});
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
    selector: 'jhi-resource-popup',
    template: ''
})
export class ResourcePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private resourcePopupService: ResourcePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.resourcePopupService
                    .open(ResourceDialogComponent as Component, params['id']);
            } else {
                this.resourcePopupService
                    .open(ResourceDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

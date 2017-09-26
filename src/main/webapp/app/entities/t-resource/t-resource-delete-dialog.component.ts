import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TResource } from './t-resource.model';
import { TResourcePopupService } from './t-resource-popup.service';
import { TResourceService } from './t-resource.service';

@Component({
    selector: 'jhi-t-resource-delete-dialog',
    templateUrl: './t-resource-delete-dialog.component.html'
})
export class TResourceDeleteDialogComponent {

    tResource: TResource;

    constructor(
        private tResourceService: TResourceService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.tResourceService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tResourceListModification',
                content: 'Deleted an tResource'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-t-resource-delete-popup',
    template: ''
})
export class TResourceDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tResourcePopupService: TResourcePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.tResourcePopupService
                .open(TResourceDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

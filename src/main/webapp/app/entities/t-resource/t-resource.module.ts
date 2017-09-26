import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TshopSharedModule } from '../../shared';
import {
    TResourceService,
    TResourcePopupService,
    TResourceComponent,
    TResourceDetailComponent,
    TResourceDialogComponent,
    TResourcePopupComponent,
    TResourceDeletePopupComponent,
    TResourceDeleteDialogComponent,
    tResourceRoute,
    tResourcePopupRoute,
    TResourceResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...tResourceRoute,
    ...tResourcePopupRoute,
];

@NgModule({
    imports: [
        TshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TResourceComponent,
        TResourceDetailComponent,
        TResourceDialogComponent,
        TResourceDeleteDialogComponent,
        TResourcePopupComponent,
        TResourceDeletePopupComponent,
    ],
    entryComponents: [
        TResourceComponent,
        TResourceDialogComponent,
        TResourcePopupComponent,
        TResourceDeleteDialogComponent,
        TResourceDeletePopupComponent,
    ],
    providers: [
        TResourceService,
        TResourcePopupService,
        TResourceResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TshopTResourceModule {}

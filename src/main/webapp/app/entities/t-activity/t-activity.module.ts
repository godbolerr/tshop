import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TshopSharedModule } from '../../shared';
import {
    TActivityService,
    TActivityPopupService,
    TActivityComponent,
    TActivityDetailComponent,
    TActivityDialogComponent,
    TActivityPopupComponent,
    TActivityDeletePopupComponent,
    TActivityDeleteDialogComponent,
    tActivityRoute,
    tActivityPopupRoute,
    TActivityResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...tActivityRoute,
    ...tActivityPopupRoute,
];

@NgModule({
    imports: [
        TshopSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TActivityComponent,
        TActivityDetailComponent,
        TActivityDialogComponent,
        TActivityDeleteDialogComponent,
        TActivityPopupComponent,
        TActivityDeletePopupComponent,
    ],
    entryComponents: [
        TActivityComponent,
        TActivityDialogComponent,
        TActivityPopupComponent,
        TActivityDeleteDialogComponent,
        TActivityDeletePopupComponent,
    ],
    providers: [
        TActivityService,
        TActivityPopupService,
        TActivityResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TshopTActivityModule {}

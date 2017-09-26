import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { TshopResourceModule } from './resource/resource.module';
import { TshopTResourceModule } from './t-resource/t-resource.module';
import { TshopTActivityModule } from './t-activity/t-activity.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        TshopResourceModule,
        TshopTResourceModule,
        TshopTActivityModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TshopEntityModule {}

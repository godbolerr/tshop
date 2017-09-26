import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { TResourceComponent } from './t-resource.component';
import { TResourceDetailComponent } from './t-resource-detail.component';
import { TResourcePopupComponent } from './t-resource-dialog.component';
import { TResourceDeletePopupComponent } from './t-resource-delete-dialog.component';

@Injectable()
export class TResourceResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const tResourceRoute: Routes = [
    {
        path: 't-resource',
        component: TResourceComponent,
        resolve: {
            'pagingParams': TResourceResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'tshopApp.tResource.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 't-resource/:id',
        component: TResourceDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'tshopApp.tResource.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tResourcePopupRoute: Routes = [
    {
        path: 't-resource-new',
        component: TResourcePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'tshopApp.tResource.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 't-resource/:id/edit',
        component: TResourcePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'tshopApp.tResource.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 't-resource/:id/delete',
        component: TResourceDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'tshopApp.tResource.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { TActivityComponent } from './t-activity.component';
import { TActivityDetailComponent } from './t-activity-detail.component';
import { TActivityPopupComponent } from './t-activity-dialog.component';
import { TActivityDeletePopupComponent } from './t-activity-delete-dialog.component';

@Injectable()
export class TActivityResolvePagingParams implements Resolve<any> {

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

export const tActivityRoute: Routes = [
    {
        path: 't-activity',
        component: TActivityComponent,
        resolve: {
            'pagingParams': TActivityResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'tshopApp.tActivity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 't-activity/:id',
        component: TActivityDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'tshopApp.tActivity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tActivityPopupRoute: Routes = [
    {
        path: 't-activity-new',
        component: TActivityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'tshopApp.tActivity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 't-activity/:id/edit',
        component: TActivityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'tshopApp.tActivity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 't-activity/:id/delete',
        component: TActivityDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'tshopApp.tActivity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
